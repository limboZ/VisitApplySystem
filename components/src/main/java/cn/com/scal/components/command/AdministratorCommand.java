package cn.com.scal.components.command;

import cn.com.scal.components.domain.TAdministratorEntity;
import cn.com.scal.components.dto.TAdministratorDTO;
import cn.com.scal.components.utils.StringUtil;

public class AdministratorCommand extends BaseCommand<TAdministratorEntity, TAdministratorDTO> {
    private String dataMark;
    private String employeeNum;

    public String getDataMark() {
        return dataMark;
    }

    public void setDataMark(String dataMark) {
        this.dataMark = dataMark;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String conditions() {
        StringBuffer queryHql = new StringBuffer();
        queryMap.clear();

        if(!StringUtil.isEmpty(dataMark)){
            queryHql.append(" and ").append("data_mark = :dataMark");
            queryMap.put("dataMark", dataMark);
        }

        if(!StringUtil.isEmpty(employeeNum)){
            queryHql.append(" and ").append("employee_num = :employeeNum");
            queryMap.put("employeeNum", employeeNum);
        }
        return queryHql.toString();
    }
}

