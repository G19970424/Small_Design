package cn.com.small_design.service;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.controller.base.dto.RegisterDto;

/**
 * @author gejj
 * @createTime 2024年04月09日 16:32
 * @version 1.0
 */
public interface IRegisterService {

    RestResponse<?> register(RegisterDto registerDto);
}
