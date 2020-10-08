package com.example.couriertracking.converter;

import com.example.couriertracking.form.InfoAddForm;
import com.example.couriertracking.vo.CourierVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InfoAddFormToCourierVoConverterTest {

    private InfoAddFormToCourierVoConverter infoAddFormToCourierVoConverter;

    @Before
    public void setUp() {
        infoAddFormToCourierVoConverter = new InfoAddFormToCourierVoConverter();
    }

    @Test
    public void should_convert() {
        //given
        InfoAddForm infoAddForm = new InfoAddForm();
        infoAddForm.setCourierId(1L);
        infoAddForm.setLatitude(41.0066851);
        infoAddForm.setLongitude(23.002531);

        //when
        CourierVo courierVo = infoAddFormToCourierVoConverter.convertToVo(infoAddForm);

        //then
        assertThat(courierVo.getId()).isEqualTo(1L);
        assertThat(courierVo.getLatitude()).isEqualTo(41.0066851);
        assertThat(courierVo.getLongitude()).isEqualTo(23.002531);
    }
}
