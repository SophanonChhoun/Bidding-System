package com.example.demo.model.request.admin.item;

import com.example.demo.model.request.admin.AdminListRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminListItemRequest extends AdminListRequest {

    private Character finished;

}
