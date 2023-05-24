package cn.ft.ckn.fastmapper.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API异常提示
 * @author ckn
 */
public class ApiException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(ApiException.class);
    private static final long serialVersionUID = 1L;

    public ApiException(String errorMsg, Object... params) {
        super(StrUtil.format(errorMsg, ArrayUtil.wrap(params)));
        logger.error(StrUtil.format(errorMsg, ArrayUtil.wrap(params)));
    }
}
