package Classes;

public class Setting {

    public int[] events_count ={0,0,0};
    public String[] names = {"Event A", "Event B", "Event C"};
    public String[] events_list_namesEvent;
    public String[] events_list_counter;
    static int counter = 0;
    int max_count = 5;

    public void Set_Counter_Names(String[] Names_str) {
        names[0] = Names_str[0];
        names[1] = Names_str[1];
        names[2] = Names_str[2];
    }

    public void Update_list() {
        for (int i = 0; i < events_list_counter.length; i++) {
            switch (events_list_counter[i]) {
                case "1":
                    events_list_namesEvent[i] = names[0];
                    break;
                case "2":
                    events_list_namesEvent[i] = names[1];
                    break;
                case "3":
                    events_list_namesEvent[i] = names[2];
                    break;
            }
        }
    }

    public String[] Get_Names() {
        return names;
    }

    public int[] Get_Events_Count() {
        return events_count;
    }

    public String[] Get_Events_List_Names() {
        return events_list_namesEvent;
    }

    public String[] Get_Events_List_Counter() {
        return events_list_counter;
    }

    public void Inc_Counter(int event){
        counter++;
        events_count[event]++;
        events_list_counter[events_list_counter.length-1] = Integer.toString(event+1);

    }

    public int Get_Counter(){
        return counter;
    }

    public int Get_Max_Count(){
        return max_count;
    }

    public void Set_Max_Count(int count){
        max_count = count;
    }

}
