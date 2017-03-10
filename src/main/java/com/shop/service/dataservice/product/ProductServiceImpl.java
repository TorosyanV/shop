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
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;


    @Autowired
    private CompositionRepository compositionRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ImageRepository imageRepository;


    @Override
    @Transactional
    public long add(ProductCreateDto productCreateDto) throws ImageStorageException {

        List<String> imageNames = new ArrayList<>();
        for (MultipartFile file : productCreateDto.getImages()) {
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

        UserEntity userEntity = userRepository.findOne(productCreateDto.getUser());
        CityEntity cityEntity = cityRepository.findOne(productCreateDto.getCity());

        ProductEntity productEntity = new ProductEntity();

        ProductDetailEntity detailEntity = new ProductDetailEntity();
        detailEntity.setProduct(productEntity);
        detailEntity.setCustomCleared(productCreateDto.isCustomCleared());

        if (productCreateDto.getUserMessage() != null) {
            if (productCreateDto.getUserMessage().toLowerCase().contains("shtap") || productCreateDto.getUserMessage().toLowerCase().contains("շտապ")) {
                detailEntity.setImmediately(true);
            }
        }

        detailEntity.setDamaged(false);
        detailEntity.setMileage(productCreateDto.getMileage());
        detailEntity.setYear(productCreateDto.getYear());
        detailEntity.setUserMessage(productCreateDto.getUserMessage());
        detailEntity.setPrice(productCreateDto.getPrice());
        detailEntity.setSellerPhone(productCreateDto.getSellerPhone());

//Only for now
        detailEntity.setImmediately(false);




        List<ImageEntity> imageEntities = new ArrayList<>();
        for (String imageName : imageNames) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setProduct(productEntity);
            imageEntity.setImageName(imageName);
            imageEntities.add(imageEntity);
        }
        productEntity.setImages(imageEntities);
        if (imageEntities.size() != 0) {
            productEntity.setMainImage(imageEntities.stream().findFirst().get());
        }

        productEntity.setUser(userEntity);
        productEntity.setCity(cityEntity);
        productEntity.setDetail(detailEntity);
        List<CompositionEntity> equipmentEntityList = compositionRepository.findAll(productCreateDto.getCompositions());
        productEntity.setCompositions(equipmentEntityList);
        productRepository.save(productEntity);
        return productEntity.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductWithDetailDto getProductWithDetail(long id) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findOne(id);

        if (productEntity == null || productEntity.isDeleted())
            throw new ProductNotFoundException(String.format("Product not exists with id %s", id));

        ProductWithDetailDto productWithDetailDto = new ProductWithDetailDto();

        ProductDetailDto productDetail = mapper.map(productEntity.getDetail(), ProductDetailDto.class);
        RegionSimpleDto regionSimpleDto = mapper.map(productEntity.getCity().getRegion(), RegionSimpleDto.class);
        CitySimpleDto citySimpleDto = mapper.map(productEntity.getCity(), CitySimpleDto.class);
        ImageUrl mainImage = null;
        if (productEntity.getMainImage() != null) {
            mainImage = mapper.map(productEntity.getMainImage(), ImageSimpleDto.class);
        } else {
            mainImage = new ImageDefaultSimpleDto();
        }

        List<ImageSimpleDto> imageSimpleDtos = DozerExtension.map(mapper, productEntity.getImages(), ImageSimpleDto.class);
        List<CompositionSimpleDto> equipmentSimpleDtos = DozerExtension.map(mapper, productEntity.getCompositions(), CompositionSimpleDto.class);


        productWithDetailDto.setProductId(productEntity.getId());
        productWithDetailDto.setDetail(productDetail);
        productWithDetailDto.setRegion(regionSimpleDto);
        productWithDetailDto.setCity(citySimpleDto);
        productWithDetailDto.setCompositions(equipmentSimpleDtos);

        productWithDetailDto.setImages(imageSimpleDtos);
        productWithDetailDto.setMainImage(mainImage);
        return productWithDetailDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> getAllByUserId(Long userId) {

        //TODO
        Pageable limit = new PageRequest(0, 100);
        return productRepository.findAllByUserIdAndDeletedIsFalse(userId, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity getByIdAndUserId(Long id, Long userId) throws InvalidProductOwnerException {
        ProductEntity productEntity = productRepository.findByIdAndUserIdAndDeletedIsFalse(id, userId);
        if (productEntity == null)
            throw new InvalidProductOwnerException(String.format("User with id %s can't edit product with id %s", userId, id));

        return productEntity;
    }


    @Override
    @Transactional(readOnly = true)
    public ProductEntity getById(Long id) {
        return productRepository.findByIdAndDeletedIsFalse(id);

    }

    @Override
    @Transactional
    public void delete(Long productId, Long userId) throws InvalidProductOwnerException {
        ProductEntity productEntity = this.getByIdAndUserId(productId, userId);

        productEntity.setDeleted(true);
        productRepository.save(productEntity);
    }

    @Override
    public Long getCountByOwnerRole(String role) {
        return productRepository.countByUserRolesName(role);
    }

    @Override
    public long getAllCount() {
        return productRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkOwnerOfProduct(Long productId, Long userId) {
        ProductEntity productEntity = productRepository.findOne(productId);
        return productEntity.getUser().getId() == userId;
    }

    @Override
    @Transactional
    public void edit(ProductEditDto productEditDto, boolean isAdmin) throws ImageStorageException, InvalidProductOwnerException {
        ProductEntity productEntity = null;
        if (isAdmin) {

            productEntity = getById(productEditDto.getProductId());
        } else {
            productEntity = getByIdAndUserId(productEditDto.getProductId(), productEditDto.getUser());
        }

        List<String> upladedImageNames = new ArrayList<>();
        for (MultipartFile file : productEditDto.getImages()) {
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

        CityEntity cityEntity = cityRepository.findOne(productEditDto.getCity());


        ProductDetailEntity detailEntity = productEntity.getDetail();
//        detailEntity.setProduct(productEntity);
        detailEntity.setCustomCleared(productEditDto.isCustomCleared());
        detailEntity.setDamaged(false);

        if (productEditDto.getUserMessage() != null) {
            if (productEditDto.getUserMessage().toLowerCase().contains("shtap") || productEditDto.getUserMessage().toLowerCase().contains("շտապ")) {
                detailEntity.setImmediately(true);
            }
        }

        detailEntity.setMileage(productEditDto.getMileage());
        detailEntity.setYear(productEditDto.getYear());
        detailEntity.setUserMessage(productEditDto.getUserMessage());
        detailEntity.setPrice(productEditDto.getPrice());
        detailEntity.setPrice(productEditDto.getPrice());
        detailEntity.setSellerPhone(productEditDto.getSellerPhone());






        List<ImageEntity> newAddedImages = new ArrayList<>();
        for (String imageName : upladedImageNames) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setProduct(productEntity);
            imageEntity.setImageName(imageName);
            newAddedImages.add(imageEntity);
        }

        List<ImageEntity> oldImages = imageRepository.findAll(productEditDto.getOldImages());
        List<ImageEntity> allImages = new ArrayList<>();
        allImages.addAll(oldImages);
        allImages.addAll(newAddedImages);


        List<ImageEntity> imagesToDelete = productEntity.getImages().stream().filter(imageEntity -> productEditDto.getOldImages() == null || !productEditDto.getOldImages().contains(imageEntity.getId())).collect(Collectors.toList());
        productEntity.setImages(allImages);

        if (allImages.size() == 0) {
            productEntity.setMainImage(null);
        } else {
            productEntity.setMainImage(allImages.get(0));

        }

        imageRepository.delete(imagesToDelete);
        imageRepository.save(allImages);


        productEntity.setCity(cityEntity);
        productEntity.setDetail(detailEntity);
        List<CompositionEntity> compositionEntityList = compositionRepository.findAll(productEditDto.getCompositions());
        productEntity.setCompositions(compositionEntityList);
        productRepository.save(productEntity);

    }


}



