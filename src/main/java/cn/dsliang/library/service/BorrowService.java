package cn.dsliang.library.service;

import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Reader;

public interface BorrowService {
    void borrow(Reader reader, Collection collection);
}
