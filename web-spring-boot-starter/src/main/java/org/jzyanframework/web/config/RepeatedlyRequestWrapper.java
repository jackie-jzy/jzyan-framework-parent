package org.jzyanframework.web.config;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>
 * 设置重复读取body数据
 * </p>
 *
 * @author jzyan
 * @since 2023-11-15
 */
public class RepeatedlyRequestWrapper extends HttpServletRequestWrapper {
    private static final String ENCODE_UTF8 = "UTF-8";
    /**
     * 缓存body数据
     */
    private byte[] body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public RepeatedlyRequestWrapper(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super(request);
        request.setCharacterEncoding(ENCODE_UTF8);
        response.setCharacterEncoding(ENCODE_UTF8);
        body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    /**
     * 重新包装输入流
     *
     * @return
     */
    @Override
    public ServletInputStream getInputStream() {
        InputStream bodyStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bodyStream.read();
            }

            /**
             * 下面的方法一般情况下不会被使用，如果你引入了一些需要使用ServletInputStream的外部组件，可以重点关注一下。
             * @return
             */
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
