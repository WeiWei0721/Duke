package com.weiwei.util;


import com.weiwei.common.Messages;
import com.weiwei.exception.BusinessException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String parseDateForDisplay(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);
    }

    public static String parseDateForStorage(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public static LocalDateTime parseStringDateTimeFromText(String dataTime) throws BusinessException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dataTime, formatter);
        }catch (Exception e){
            throw new BusinessException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
