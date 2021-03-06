package com.example.mall.controller;

import com.example.mall.common.api.CommonPage;
import com.example.mall.common.api.CommonResult;
import com.example.mall.mbg.model.PmsBrand;
import com.example.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 品牌管理
 */
@Slf4j
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {

  private final PmsBrandService demoService;

  public PmsBrandController(PmsBrandService demoService) {
    this.demoService = demoService;
  }

  @PreAuthorize("hasAuthority('pms:brand:read')")
  @ApiOperation("获取所有品牌列表")
  @RequestMapping(value = "listAll", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<PmsBrand>> getBrandList() {
    return CommonResult.success(demoService.listAllBrand());
  }

  @ApiOperation("添加品牌")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<PmsBrand> createBrand(@RequestBody PmsBrand pmsBrand) {
    CommonResult<PmsBrand> commonResult;
    int count = demoService.createBrand(pmsBrand);
    if (count == 1) {
      commonResult = CommonResult.success(pmsBrand);
      log.debug("createBrand success:{}", pmsBrand);
    } else {
      commonResult = CommonResult.failed("操作失败");
      log.debug("createBrand failed:{}", pmsBrand);
    }
    return commonResult;
  }

  @ApiOperation("更新指定 id 品牌信息")
  @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<PmsBrand> updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto,
      BindingResult result) {
    CommonResult<PmsBrand> commonResult;
    int count = demoService.updateBrand(id, pmsBrandDto);
    if (count == 1) {
      commonResult = CommonResult.success(pmsBrandDto);
      log.debug("updateBrand success:{}", pmsBrandDto);
    } else {
      commonResult = CommonResult.failed("操作失败");
      log.debug("updateBrand failed:{}", pmsBrandDto);
    }
    return commonResult;
  }

  @ApiOperation("删除指定 id 的品牌")
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<Object> deleteBrand(@PathVariable("id") Long id) {
    int count = demoService.deleteBrand(id);
    if (count == 1) {
      log.debug("deleteBrand success :id={}", id);
      return CommonResult.success(null);
    } else {
      log.debug("deleteBrand failed :id={}", id);
      return CommonResult.failed("操作失败");
    }
  }

  @ApiOperation("分页查询品牌列表")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<CommonPage<PmsBrand>> listBrand(
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
    List<PmsBrand> brandList = demoService.listBrand(pageNum, pageSize);
    return CommonResult.success(CommonPage.restPage(brandList));
  }

  @ApiOperation("获取指定 id 的品牌详情")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
    return CommonResult.success(demoService.getBrand(id));
  }
}
