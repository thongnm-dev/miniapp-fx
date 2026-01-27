package dev.thongnm.model;

import lombok.Data;

import java.util.Date;

@Data
public class AwsItem {

    private String name;

    private String type;

    private Date modified;
}
