package com.winter.yxssoft.service;

import com.winter.yxssoft.entity.AdminUser;
import com.winter.yxssoft.utils.PageResult;
import com.winter.yxssoft.utils.PageUtil;


public interface AdminUserService {
    PageResult getAdminUserPage(PageUtil pageUtil);
}
