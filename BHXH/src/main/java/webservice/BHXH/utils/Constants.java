package webservice.BHXH.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface Constants {
    public final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
}
