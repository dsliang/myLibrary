package cn.dsliang.library.service;

import cn.dsliang.library.entity.Circulating;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Reader;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BorrowService {
    void borrow(Reader reader, Collection collection);

    List<Circulating> getCirculating(Integer readerId);

    Page<Circulating> getCirculating(Integer readerId, Integer page, Integer size);
}
