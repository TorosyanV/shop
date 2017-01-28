package com.shop.service.dataservice.product;


import com.shop.data.entity.*;
import com.shop.data.repository.*;
import com.shop.service.dto.ImageSimpleDto;
import com.shop.service.dto.product.ProductCreateDto;
import com.shop.service.dto.product.ProductDetailDto;
import com.shop.service.dto.product.ProductEditDto;
import com.shop.service.storage.ImageStorageException;
import com.shop.helper.DozerExtension;
import com.shop.service.dto.CompositionSimpleDto;
import com.shop.service.dto.ImageDefaultSimpleDto;
import com.shop.service.dto.ImageUrl;
import com.shop.service.dto.product.ProductWithDetailDto;
import com.shop.service.dto.location.CitySimpleDto;
import com.shop.service.dto.location.RegionSimpleDto;
import com.shop.service.storage.ImageStorage;
import com.shop.util.ImageUtil;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vazgen on 08/17/2016.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());
    @Autowired
    private ImageStorage imageStorage;

    @Autowired
    private Mapper mapper;
    @Autowired
    private ProductRepository carRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;



    @Autowired
    private CompositionRepository equipmentRepository;

    @Autowired
    private ProductDetailRepository carDetailRepository;

    @Autowired
    private ImageRepository imageRepository;


    @Override
    @Transactional
    public long add(ProductCreateDto carCreateDto) throws ImageStorageException {

        List<String> imageNames = new ArrayList<>();
        for (MultipartFile file : carCreateDto.getImages()) {
            if (file.isEmpty())
                continue;


            if (file.getContentType().equals("image/webp")) {
                logger.info("Found image/webp, ignoring image");
                continue;
            }

            String name = ImageUtil.generateRandomImageName(10);
            imageStorage.save(file, name, "jpg");
            imageNames.add(name);
        }

        UserEntity userEntity = userRepository.findOne(carCreateDto.getUser());
        CityEntity cityEntity = cityRepository.findOne(carCreateDto.getCity());

        ProductEntity carEntity = new ProductEntity();

        ProductDetailEntity detailEntity = new ProductDetailEntity();
        detailEntity.setCar(carEntity);
        detailEntity.setCustomCleared(carCreateDto.isCustomCleared());

        if (carCreateDto.getUserMessage() != null) {
            if (carCreateDto.getUserMessage().toLowerCase().contains("shtap") || carCreateDto.getUserMessage().toLowerCase().contains("շտապ")) {
                detailEntity.setImmediately(true);
            }
        }

        detailEntity.setDamaged(false);
        detailEntity.setMileage(carCreateDto.getMileage());
        detailEntity.setYear(carCreateDto.getYear());
        detailEntity.setUserMessage(carCreateDto.getUserMessage());
        detailEntity.setPrice(carCreateDto.getPrice());
        detailEntity.setSellerPhone(carCreateDto.getSellerPhone());

//Only for now
        detailEntity.setImmediately(false);

    ;


        List<ImageEntity> imageEntities = new ArrayList<>();
        for (String imageName : imageNames) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setCar(carEntity);
            imageEntity.setImageName(imageName);
            imageEntities.add(imageEntity);
        }
        carEntity.setImages(imageEntities);
        if (imageEntities.size() != 0) {
            carEntity.setMainImage(imageEntities.stream().findFirst().get());
        }

        carEntity.setUser(userEntity);
        carEntity.setCity(cityEntity);
        carEntity.setDetail(detailEntity);
        List<CompositionEntity> equipmentEntityList = equipmentRepository.findAll(carCreateDto.getEquipments());
        carEntity.setEquipments(equipmentEntityList);
        carRepository.save(carEntity);
        return carEntity.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductWithDetailDto getCarWithDetail(long id) throws ProductNotFoundException {
        ProductEntity carEntity = carRepository.findOne(id);

        if (carEntity == null || carEntity.isDeleted())
            throw new ProductNotFoundException(String.format("Car not exists with id %s", id));

        ProductWithDetailDto carWithDetail = new ProductWithDetailDto();

        ProductDetailDto carDetail = mapper.map(carEntity.getDetail(), ProductDetailDto.class);
        RegionSimpleDto regionSimpleDto = mapper.map(carEntity.getCity().getRegion(), RegionSimpleDto.class);
        CitySimpleDto citySimpleDto = mapper.map(carEntity.getCity(), CitySimpleDto.class);
        ImageUrl mainImage = null;
        if (carEntity.getMainImage() != null) {
            mainImage = mapper.map(carEntity.getMainImage(), ImageSimpleDto.class);
        } else {
            mainImage = new ImageDefaultSimpleDto();
        }

        List<ImageSimpleDto> imageSimpleDtos = DozerExtension.map(mapper, carEntity.getImages(), ImageSimpleDto.class);
        List<CompositionSimpleDto> equipmentSimpleDtos = DozerExtension.map(mapper, carEntity.getEquipments(), CompositionSimpleDto.class);


        carWithDetail.setCarId(carEntity.getId());
        carWithDetail.setDetail(carDetail);
        carWithDetail.setRegion(regionSimpleDto);
        carWithDetail.setCity(citySimpleDto);
        carWithDetail.setEquipments(equipmentSimpleDtos);

        carWithDetail.setImages(imageSimpleDtos);
        carWithDetail.setMainImage(mainImage);
        return carWithDetail;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> getAllByUserId(Long userId) {

        //TODO
        Pageable limit = new PageRequest(0, 100);
        return carRepository.findAllByUserIdAndDeletedIsFalse(userId, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity getByIdAndUserId(Long id, Long userId) throws InvalidProductOwnerException {
        ProductEntity carEntity = carRepository.findByIdAndUserIdAndDeletedIsFalse(id, userId);
        if (carEntity == null)
            throw new InvalidProductOwnerException(String.format("User with id %s can't edit car with id %s", userId, id));

        return carEntity;
    }


    @Override
    @Transactional(readOnly = true)
    public ProductEntity getById(Long id) {
        return carRepository.findByIdAndDeletedIsFalse(id);

    }

    @Override
    @Transactional
    public void delete(Long carId, Long userId) throws InvalidProductOwnerException {
        ProductEntity carEntity = this.getByIdAndUserId(carId, userId);

        carEntity.setDeleted(true);
        carRepository.save(carEntity);
    }

    @Override
    public Long getCountByOwnerRole(String role) {
        return carRepository.countByUserRolesName(role);
    }

    @Override
    public long getAllCount() {
        return carRepository.count();
    }

    @Override
    @Transactional
    public void edit(ProductEditDto carEditDto, boolean isAdmin) throws ImageStorageException, InvalidProductOwnerException {
        ProductEntity carEntity = null;
        if (isAdmin) {

            carEntity = getById(carEditDto.getCarId());
        } else {
            carEntity = getByIdAndUserId(carEditDto.getCarId(), carEditDto.getUser());
        }

        List<String> upladedImageNames = new ArrayList<>();
        for (MultipartFile file : carEditDto.getImages()) {
            if (file.isEmpty())
                continue;


            if (file.getContentType().equals("image/webp")) {
                logger.info("Found image/webp, ignoring image");
                continue;
            }

            String name = ImageUtil.generateRandomImageName(10);
            imageStorage.save(file, name, "jpg");
            upladedImageNames.add(name);
        }

        CityEntity cityEntity = cityRepository.findOne(carEditDto.getCity());


        ProductDetailEntity detailEntity = carEntity.getDetail();
//        detailEntity.setCar(carEntity);
        detailEntity.setCustomCleared(carEditDto.isCustomCleared());
        detailEntity.setDamaged(false);

        if (carEditDto.getUserMessage() != null) {
            if (carEditDto.getUserMessage().toLowerCase().contains("shtap") || carEditDto.getUserMessage().toLowerCase().contains("շտապ")) {
                detailEntity.setImmediately(true);
            }
        }

        detailEntity.setMileage(carEditDto.getMileage());
        detailEntity.setYear(carEditDto.getYear());
        detailEntity.setUserMessage(carEditDto.getUserMessage());
        detailEntity.setPrice(carEditDto.getPrice());
        detailEntity.setPrice(carEditDto.getPrice());
        detailEntity.setSellerPhone(carEditDto.getSellerPhone());






        List<ImageEntity> newAddedImages = new ArrayList<>();
        for (String imageName : upladedImageNames) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setCar(carEntity);
            imageEntity.setImageName(imageName);
            newAddedImages.add(imageEntity);
        }

        List<ImageEntity> oldImages = imageRepository.findAll(carEditDto.getOldImages());
        List<ImageEntity> allImages = new ArrayList<>();
        allImages.addAll(oldImages);
        allImages.addAll(newAddedImages);


        List<ImageEntity> imagesToDelete = carEntity.getImages().stream().filter(imageEntity -> carEditDto.getOldImages() == null || !carEditDto.getOldImages().contains(imageEntity.getId())).collect(Collectors.toList());
        carEntity.setImages(allImages);

        if (allImages.size() == 0) {
            carEntity.setMainImage(null);
        } else {
            carEntity.setMainImage(allImages.get(0));

        }

        imageRepository.delete(imagesToDelete);
        imageRepository.save(allImages);


        carEntity.setCity(cityEntity);
        carEntity.setDetail(detailEntity);
        List<CompositionEntity> equipmentEntityList = equipmentRepository.findAll(carEditDto.getEquipments());
        carEntity.setEquipments(equipmentEntityList);
        carRepository.save(carEntity);

    }


}



