package yjs.gd.com.gdandroidsample.fragment;

import java.util.List;

/**
 * Created by jesson on 2017/12/5.
 */

public interface IInputReceiver {


    public void updateContent(List<String> result);
    public int getMaxCount();
    public List<String> getContentList();
}
