package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.Utils.FdfsUtils;
import com.example.server.mapper.AttachMapper;
import com.example.server.pojo.Attach;
import com.example.server.service.IAttachService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Service
public class AttachServiceImpl extends ServiceImpl<AttachMapper, Attach> implements IAttachService {

    @Autowired
    private AttachMapper attachMapper;
    @Override
    public void downloadFile(Integer id, HttpServletResponse response) throws IOException {
        Attach attach = attachMapper.selectById(id);
        String groupName = "group1";
        String url = attach.getFkey();
        String remoteFileName = url.substring(url.lastIndexOf("M00/00/00/"));
        String fileName = attach.getFname();
        BufferedInputStream br = new BufferedInputStream(FdfsUtils.downFile(groupName,remoteFileName));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset();
        response.setContentType("application/x-msdownload");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        IOUtils.closeQuietly(br);
        IOUtils.closeQuietly(out);
        Map<String, Integer> map = new HashMap<>();
    }
}
