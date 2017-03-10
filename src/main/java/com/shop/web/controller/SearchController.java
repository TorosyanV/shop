package com.shop.web.controller;

import com.shop.data.entity.ProductEntity;
import com.shop.data.specification.filter.SearchFilter;
import com.shop.service.dataservice.region.RegionService;
import com.shop.service.search.SearchService;
import com.shop.util.Pager;
import com.shop.web.viewmodel.SearchRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by zhirayrg on 3/10/2017.
 */


@Controller
public class SearchController {

    private static final Logger logger = Logger.getLogger(SearchController.class.getName());

    @Autowired
    private RegionService regionService;

    @Autowired
    private SearchService searchService;


    @GetMapping("/search")
    public String search(@ModelAttribute SearchRequest request, ModelMap modelMap, Device device) {

        //when redirected from home
        if (request.getPage() == 0) {
            request.setPage(1);
        }
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setCityId(request.getCity());
        searchFilter.setMinPrice(request.getMinPrice());
        searchFilter.setMaxPrice(request.getMaxPrice());
        searchFilter.setRegionId(request.getRegion());
        searchFilter.setCountryId(1L);//Armenia only

        Page<ProductEntity> page = searchService.search(searchFilter, new PageRequest(request.getPage() - 1, 10));

        if (!page.hasContent() && request.getPage()>1) {
            logger.info(String.format("no data in page %s, redirecting 404", request.getPage()));
            return "redirect:/error/404";
        }


        modelMap.addAttribute("products", page.getContent());
        modelMap.addAttribute("pager", new Pager((int) page.getTotalElements(), page.getNumber() + 1, 10, "search"));
        modelMap.addAttribute("device", device);
        modelMap.addAttribute("total", page.getTotalElements());


        return "search";
    }

    @PostMapping("/searchAjax")
    public String searchAjax(@RequestBody SearchRequest request, ModelMap modelMap, Device device) {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setCityId(request.getCity());
        searchFilter.setMinPrice(request.getMinPrice());
        searchFilter.setMaxPrice(request.getMaxPrice());
        searchFilter.setRegionId(request.getRegion());

        searchFilter.setCountryId(1L);//Armenia only
        Page<ProductEntity> page = searchService.search(searchFilter, new PageRequest(request.getPage() - 1, 10));
        modelMap.addAttribute("products", page.getContent());
        modelMap.addAttribute("pager", new Pager((int) page.getTotalElements(), page.getNumber() + 1, 10, "search"));
        modelMap.addAttribute("total", page.getTotalElements());
        modelMap.addAttribute("device", device);
        return "partial/search";
    }

    @GetMapping("/top")
    public String getTop(@ModelAttribute SearchRequest request, ModelMap modelMap, Device device) {

        //when redirected from home
        if (request.getPage() == 0) {
            request.setPage(1);
        }
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setCityId(request.getCity());
        searchFilter.setMinPrice(request.getMinPrice());
        searchFilter.setMaxPrice(request.getMaxPrice());
        searchFilter.setRegionId(request.getRegion());
        searchFilter.setCountryId(1L);//Armenia only

        Page<ProductEntity> page = searchService.search(searchFilter, new PageRequest(request.getPage() - 1, 30));
        modelMap.addAttribute("topProducts", page.getContent());
        modelMap.addAttribute("device", device);
        modelMap.addAttribute("total", page.getTotalElements());



        return "partial/top";
    }

}
