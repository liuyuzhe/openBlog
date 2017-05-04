package com.strongliu.blog.service;

import com.strongliu.blog.dao.AttachDao;
import com.strongliu.blog.entity.Attach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuyuzhe on 2017/5/4.
 */

@Service
public class AttachService {

    @Autowired
    private AttachDao attachDao;

    public Attach findAttachById(int id) {
        return attachDao.selectById(id);
    }

    public List<Attach> findAllAttach(int pageId, int pageSize) {
        pageId = pageId < 0 ? 1 : pageId;
        int startIndex = (pageId - 1) * pageSize;
        return attachDao.selectAll(startIndex, pageSize);
    }

    public int pageTotal(int pageSize) {
        int postTotal = attachDao.selectCount();
        int pageTotal = postTotal / pageSize;
        if (postTotal % pageSize != 0) {
            pageTotal += 1;
        }

        return pageTotal;
    }

    public int addAttach(Attach attach) {
        return attachDao.insert(attach);
    }

    public int removeAttach(int id) {
        return attachDao.deleteById(id);
    }
}
