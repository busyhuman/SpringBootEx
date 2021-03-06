package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {

    @Test
    public void creation() {
        Region region = Region.builder().name("서울").build();

        assertThat(region.getName(), is("서울"));
   }
}