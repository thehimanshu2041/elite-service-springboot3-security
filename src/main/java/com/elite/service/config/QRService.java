package com.elite.service.config;

import java.util.HashMap;

public interface QRService {

    byte[] generateQRCode(HashMap<String, String> userDetails, int width, int height);
}
