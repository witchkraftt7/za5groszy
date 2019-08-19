package com.za5groszy.application;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

@Service
public class ApplicationEncoderService {
    private static final String SALT = "defaultSalt";
    private static final Hashids hashids = new Hashids(SALT);

    public String encode(int id) {
        return hashids.encode(id);
    }

    public int decode(String encodedId) {
        return (int) hashids.decode(encodedId)[0];
    }
}
