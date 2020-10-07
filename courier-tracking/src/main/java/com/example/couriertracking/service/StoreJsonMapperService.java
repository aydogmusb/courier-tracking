package com.example.couriertracking.service;

import com.example.couriertracking.entity.Store;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class StoreJsonMapperService {

    public List<Store> mapToStore() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        List<Store> storeList = objectMapper.readValue(
                new File("/courier-tracking/src/main/resources/store.json"),
                new TypeReference<>() {
                });
        return storeList;
    }
}
