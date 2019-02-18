package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.*;
import cn.dsliang.library.enums.CollectionStatusEnum;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.repository.CirculatingRepository;
import cn.dsliang.library.repository.CirculationRecordRepository;
import cn.dsliang.library.repository.BorrowRepository;
import cn.dsliang.library.service.BorrowService;
import cn.dsliang.library.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private CirculationRecordRepository circulationRecordRepository;

    @Autowired
    private CirculatingRepository circulatingRepository;

    @Override
    @Transactional
    public void borrow(Reader reader, Collection collection) {

        //1.读者借阅册数是否达到上限
        List<Circulating> circulations = circulatingRepository.findByReaderId(reader.getId());
        if (circulations.size() >= reader.getReaderType().getRule().getBorrowNumber()) {
            throw new BusinessException(ResultEnum.UP_TO_BORROW_LIMIT);
        }

        //2.图书状态,是否能外借
//        if (collection.getStatusEnum() != CollectionStatusEnum.NORMAL) {
//            throw new BusinessException(ResultEnum.COLLECTION_STATUS_ERROR);
//        }

        //3.修改图书状态
        collection.setStatus(CollectionStatusEnum.BORROWED.getCode());


        Date returnDate = DateUtil.addDay(DateUtil.getCurrentDate(), reader.getReaderType().getRule().getBorrowDays());
        Date borrowDate = DateUtil.getCurrentDate();

        //4.往图书流通记录表插入一条新记录
        CirculationRecord circulationRecord = new CirculationRecord();
        circulationRecord.setReaderCard(reader.getCard());
        circulationRecord.setReaderName(reader.getName());
        circulationRecord.setBarcode(collection.getBarcode());
        circulationRecord.setLocationName(collection.getLocation().getName());
        circulationRecord.setIsbn(collection.getBiblio().getIsbn());
        circulationRecord.setTitle(collection.getBiblio().getTitle());
        circulationRecord.setAuthor(collection.getBiblio().getAuthor());
        circulationRecord.setPress(collection.getBiblio().getPress());
        circulationRecord.setBorrowDate(borrowDate);
        circulationRecord.setReturnDate(returnDate);
        circulationRecord.setReturnedDate(null);
        //TODO: 2019/2/11 图书借出状态
        //circulationRecord.setStatus();

        //4.往借阅记录表插入一条新记录
        Borrow borrow = new Borrow();
        borrow.setCirculationRecord(circulationRecord);
        borrow.setBorrowDate(borrowDate);
        borrow.setBorrowDays(reader.getReaderType().getRule().getBorrowDays());

        //5.往图书流通(实时)表插入一条新记录
        Circulating circulating = new Circulating();
        circulating.setReader(reader);
        circulating.setCirculationRecord(circulationRecord);
        circulating.setCollection(collection);
        circulating.setBiblio(collection.getBiblio());
        circulating.setBorrowDate(borrowDate);
        circulating.setReturnDate(returnDate);
        circulating.setRenewalTimes(0);

        circulationRecordRepository.save(circulationRecord);
        borrowRepository.save(borrow);
        circulatingRepository.save(circulating);
    }
}
