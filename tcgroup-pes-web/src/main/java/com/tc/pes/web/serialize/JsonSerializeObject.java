
package com.tc.pes.web.serialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.*;

/**
 * @author liufei 2015年2月2日
 */

public abstract class JsonSerializeObject implements Externalizable {

    private static final String CHARSET = "utf-8";

    private static final Logger logger = LoggerFactory.getLogger(JsonSerializeObject.class);

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.write(JacksonUtils.entity2Json(this).getBytes(CHARSET));
        out.flush();
        out.close();
    }

    @Override
    public void readExternal(ObjectInput in) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {

            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            bytes = outputStream.toByteArray();

            Object obj = JacksonUtils.json2Entity(bytes, this.getClass());
            BeanUtils.copyProperties(obj, this);
        } catch (Exception e) {
            logger.warn(this.getClass().getName() + "反序列化失败");
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
