package com.skypro.courswork3.courswork3.repository;

import com.skypro.courswork3.courswork3.model.socks.Socks;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface StocksRepository {
    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();

    List<SocksBatch> getList();

    void replace(List<SocksBatch> socksBatchList);


}
