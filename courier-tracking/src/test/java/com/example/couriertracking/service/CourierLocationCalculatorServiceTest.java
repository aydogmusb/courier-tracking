package com.example.couriertracking.service;

import com.example.couriertracking.converter.InfoAddFormToCourierVoConverter;
import com.example.couriertracking.entity.Store;
import com.example.couriertracking.form.InfoAddForm;
import com.example.couriertracking.vo.CourierVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourierLocationCalculatorServiceTest {

    private CourierLocationCalculatorService courierLocationCalculatorService;

    @Mock
    private StoreJsonMapperService storeJsonMapperService;

    @Mock
    private InfoAddFormToCourierVoConverter infoAddFormToCourierVoConverter;

    @Before
    public void setUp(){courierLocationCalculatorService = new CourierLocationCalculatorService(storeJsonMapperService,
            infoAddFormToCourierVoConverter);}

    @Test
    public void should_check_courier_coordinates() throws IOException {
        //given
        InfoAddForm infoAddForm = new InfoAddForm();
        infoAddForm.setCourierId(1L);
        infoAddForm.setLatitude(41.0066851);
        infoAddForm.setLongitude(23.002531);

        Store store = new Store();
        store.setId(1L);
        store.setLatitude(31.0066851);
        store.setLongitude(41.0066851);
        store.setName("Ataşehir MMM Migros");

        CourierVo courierVo = new CourierVo();
        courierVo.setId(1L);
        courierVo.setLatitude(41.0066851);
        courierVo.setLongitude(23.002531);

        List<Store> stores = new ArrayList<>();
        stores.add(store);

        when(storeJsonMapperService.mapToStore()).thenReturn(stores);
        when(infoAddFormToCourierVoConverter.convertToVo(infoAddForm)).thenReturn(courierVo);

        //when
        Throwable throwable = catchThrowable(() -> courierLocationCalculatorService.checkCoordinates(infoAddForm));

        //then
        assertThat(throwable).isNull();

        InOrder inOrder = inOrder(storeJsonMapperService, infoAddFormToCourierVoConverter);
        inOrder.verify(storeJsonMapperService).mapToStore();
        inOrder.verify(infoAddFormToCourierVoConverter).convertToVo(infoAddForm);
        inOrder.verifyNoMoreInteractions();
    }
}
