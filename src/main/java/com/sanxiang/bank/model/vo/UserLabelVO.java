package com.sanxiang.bank.model.vo;


import com.sanxiang.bank.model.entity.UserLabel;
import lombok.Data;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
@Data
public class UserLabelVO {
    @Id
    private Long uid;
    private List<String> labels;

    public UserLabelVO(UserLabel u) {
        this.uid = u.getUid();
        List<String> labels = new ArrayList<String>();
        String labelStr = u.getLabelStr();
        int j = 0;
        for (int i = 0; i < labelStr.length(); i++) {
            if (labelStr.charAt(i) == ' ') {
                labels.add(labelStr.substring(j, i));
                j = i + 1;
            }
        }
        this.labels = labels;
    }
    public UserLabel toEntity(){
        StringBuffer sb = new StringBuffer("");
        for(int i =0;i<this.labels.size();i++){
            sb.append(this.labels.get(i));
            if (i != this.labels.size() -1) {
                sb.append(" ");
            }
        }
        return new UserLabel(this.getUid(),sb.toString());
    }
}
