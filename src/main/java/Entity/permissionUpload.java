package Entity;

public class permissionUpload {
    private String level;

    public permissionUpload(String level){
        this.level = level;
    }


    public boolean canSendRequest(String[] tab_req){
        boolean result = true;
        long time = System.currentTimeMillis();
        switch (this.level) {
            case "Noob":
                if(time - Long.parseLong(tab_req[0]) <= 60000){
                    result = false;
                }
                break;
            case "Casual":
                for (int i = 0; i < 2; i++) {
                        if((tab_req[i].equals("0"))){
                            return true;
                        }else{
                            if (time - Long.parseLong(tab_req[i]) <= 60000) {
                                result =  false;
                            }
                        }
                }
                break;
            case "Leet":
                for (int i = 0; i < 4; i++) {
                    if((tab_req[i].equals("0"))){
                        return true;
                    }else{
                        if (time - Long.parseLong(tab_req[i]) <= 60000) {
                            result =  false;
                        }
                    }
                }
                break;
        }
        return result;
    }


    public String[] tab_reqUpadted(String[] tab_req){
        String[] new_tab_req = tab_req;
        long time = System.currentTimeMillis();
        switch (this.level) {
            case "Noob" :
                new_tab_req[0] = "" + time;
                break;
            case "Casual" :
                new_tab_req[0] = tab_req[1];
                new_tab_req[1] = "" + time;
                break;
            case "Leet" :
                new_tab_req[0] = tab_req[1];
                new_tab_req[1] = tab_req[2];
                new_tab_req[2] = tab_req[3];
                new_tab_req[3] = "" + time;
                break;
        }
        return new_tab_req;
    }


}
