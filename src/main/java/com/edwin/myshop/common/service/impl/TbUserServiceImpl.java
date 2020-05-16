package com.edwin.myshop.common.service.impl;


import com.edwin.myshop.common.service.TbUserService;
import com.edwin.myshop.commons.domain.TbUser;
import com.edwin.myshop.commons.mapper.TbUserMapper;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl extends BaseCrudServiceImpl<TbUser, TbUserMapper> implements TbUserService {
}
