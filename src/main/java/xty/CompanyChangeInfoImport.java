package xty;

import bean.CompanyChangeInfo;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/11/1.
 */
public class CompanyChangeInfoImport {
    public static void main(String[] args) {
        String file = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] ts = line.split("\001");
                CompanyChangeInfo model = new CompanyChangeInfo();
                model.setId(Long.parseLong(ts[0]));
                if(StringUtils.isNotBlank(ts[1])){
                    model.setCompanyId(Long.parseLong(ts[1]));
                }
                if(StringUtils.isNotBlank(ts[2])){
                    model.setChangeItem(ts[2]);
                }
                if(StringUtils.isNotBlank(ts[3])){
                    model.setBeforeChange(ts[3]);
                }
                if(StringUtils.isNotBlank(ts[4])){
                    model.setAfterChange(ts[4]);
                }
                if(StringUtils.isNotBlank(ts[5])){
                    model.setChangeTime(Long.parseLong(ts[5]));
                }

                model.setCreateTime(System.currentTimeMillis());

                System.out.println(model);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
