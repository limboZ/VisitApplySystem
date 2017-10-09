package cn.com.scal.components.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vslimit on 15/7/29.
 */
public class FileUtil {

    public final static String FROM = "components/src/main/java/cn/com/scal/components/domain";
    public final static String TO_DTO = "components/src/main/java/cn/com/scal/components/dto/%s";
    public final static String DTO_PATH = "components/src/main/java/cn/com/scal/components/dto/%sDTO.java";
    public final static String COMMAND_PATH = "components/src/main/java/cn/com/scal/components/command/%sCommand.java";
    public final static String RESTFUL_CONTROLLER_PATH = "webservice/src/main/java/cn/com/scal/webservice/action/controller/%sController.java";
    public final static String WEB_CONTROLLER_PATH = "website/src/main/java/cn/com/scal/website/action/controller/%sController.java";

    public final static String TO_COMMOND = "components/src/main/java/cn/com/scal/components/command/%s";

    public final static String PACKAGE = "package cn.com.scal.components.%s;";
    public final static String WEBSERVICE_PACKAGE = "package cn.com.scal.webservice.%s;";
    public final static String IMPORT = "import cn.com.scal.components.domain.%s;";
    public final static String IMPORT_DTO = "import cn.com.scal.components.dto.%sDTO;";
    public final static String IMPORT_COMMAND = "import cn.com.scal.components.command.%sCommand;";

    public final static String WEB_PACKAGE = "package cn.com.scal.website.%s;";


    public final static String DTO_CODE = "public class %sDTO extends BaseDTO<%sDTO,%s> {}";
    public final static String COMMAND_CODE = "public class %sCommand extends BaseCommand<%s,%sDTO> {}";
    public final static String RESTFUL_CONTROLLER_CODE = "public class %sController extends BasicController<%s,%sDTO,%sCommand> {}";
    public final static String WEB_CONTROLLER_CODE = "public class %sController extends BasicController<%s,%sDTO,%sCommand> {}";
    public final static String RESTFUL_MAP = "@RequestMapping(\"/%ss\")";


    private final static String existFileName = "Base.java,CurrentUser.java,TLogs.java";

    public static List<String> getFiles(String filePath) {
        ArrayList<String> filelist = new ArrayList<>();
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file.getAbsolutePath());
            } else {
                filelist.add(file.getName());
            }
        }
        return filelist;
    }

    public static List<String> dtoContent(String name) {
        List<String> list = new ArrayList<>();
        list.add(String.format(PACKAGE, "dto"));
        list.add(String.format(IMPORT, name));
        list.add(String.format(DTO_CODE, name, name, name));
        return list;
    }

    public static List<String> commandContent(String name) {
        List<String> list = new ArrayList<>();
        list.add(String.format(PACKAGE, "command"));
        list.add(String.format(IMPORT, name));
        list.add(String.format(IMPORT_DTO, name));
        list.add(String.format(COMMAND_CODE, name, name,name));
        return list;
    }

    public static List<String> restfulControllerContent(String name) {
        List<String> list = new ArrayList<>();
        list.add(String.format(WEBSERVICE_PACKAGE, "action.controller"));
        list.add(String.format(IMPORT, name));
        list.add(String.format(IMPORT_DTO, name));
        list.add(String.format(IMPORT_COMMAND, name));
        list.add("import org.springframework.web.bind.annotation.RequestMapping;");
        list.add("import org.springframework.web.bind.annotation.RestController;");
        list.add("@RestController");
        list.add(String.format(RESTFUL_MAP, name.toLowerCase()));
        list.add(String.format(RESTFUL_CONTROLLER_CODE, name, name, name, name));
        return list;
    }

    public static List<String> controllerContent(String name) {
        List<String> list = new ArrayList<>();
        list.add(String.format(WEB_PACKAGE, "action.controller"));
        list.add(String.format(IMPORT, name));
        list.add(String.format(IMPORT_DTO, name));
        list.add(String.format(IMPORT_COMMAND, name));
        list.add("import org.springframework.stereotype.Controller;");
        list.add("import org.springframework.web.bind.annotation.RequestMapping;");
        list.add("@Controller");
        list.add(String.format(RESTFUL_MAP, name.toLowerCase()));
        list.add(String.format(WEB_CONTROLLER_CODE, name, name, name, name));
        return list;

    }


    public static void writeTo(File file, List<String> list) {
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (String str : list) {
                writer.write(str);
                writer.newLine();//换行
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> filelist = getFiles(FROM);
        for (String str : filelist) {
            if (!existFileName.contains(str)) {
                String name = str.substring(0, str.indexOf("."));
                File dtoFile = new File(String.format(DTO_PATH, name));
                if (!dtoFile.exists()) {
                    dtoFile.createNewFile();
                    writeTo(dtoFile, dtoContent(name));
                }
                File commandFile = new File(String.format(COMMAND_PATH, name));
                if (!commandFile.exists()) {
                    commandFile.createNewFile();
                    writeTo(commandFile, commandContent(name));
                }
                File restfulControllerFile = new File(String.format(RESTFUL_CONTROLLER_PATH, name));
                if (!restfulControllerFile.exists()) {
                    restfulControllerFile.createNewFile();
                    writeTo(restfulControllerFile, restfulControllerContent(name));
                }
                File webControllerFile = new File(String.format(WEB_CONTROLLER_PATH, name));
                if (!webControllerFile.exists()) {
                    webControllerFile.createNewFile();
                    writeTo(webControllerFile, controllerContent(name));
                }


            }
        }
    }
}
