package com.skypro.courswork3.courswork3.repository;

import com.skypro.courswork3.courswork3.model.socks.Socks;
import com.skypro.courswork3.courswork3.model.socks.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StocksRepositoryImpl implements StocksRepository{

    private HashMap<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            int quantity = socksMap.get(socks);
            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return null;
    }

    @Override
    public List<SocksBatch> getList() {
        List<SocksBatch> socksBatchesList = new ArrayList<>();
        for (Map.Entry<Socks, Integer> socksIntegerMap: socksMap.entrySet()) {
            socksBatchesList.add(new SocksBatch(socksIntegerMap.getKey(), socksIntegerMap.getValue()));
        }
        return null;
    }

    @Override
    public void replace(List<SocksBatch> socksBatchList) {
        socksMap.clear();
        for (SocksBatch batch: socksBatchList) {
            save(batch);
        }
    }
}
