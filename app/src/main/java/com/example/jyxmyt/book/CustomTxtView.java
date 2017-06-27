package com.example.jyxmyt.book;

import android.content.Context;   
import android.text.Layout;   
import android.text.Selection;   
import android.util.AttributeSet;
import android.view.ContextMenu;   
import android.view.MenuItem;
import android.view.MotionEvent;   
import android.widget.EditText;   
  
public class CustomTxtView extends EditText {   

	private final static int C_MENU_BEGIN_SELECTION = 0;
    boolean bIsBeginSelecting = false;
    int line = 0;	// ���������
    int off = 0;	// ���������
    
    private class MenuHandler implements MenuItem.OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem item) {
            return onContextMenuItem(item.getItemId());
        }
    }
    
    public boolean onContextMenuItem(int id) {
    	switch (id) {
    	case C_MENU_BEGIN_SELECTION:
    		bIsBeginSelecting = true;
    		setCursorVisible(true);
    		return true;
    	}
    	
		return false;
    }
    
    public CustomTxtView(Context context, AttributeSet attrs) {
        super(context, attrs); //, 16842884
    }
    
    public CustomTxtView(Context context) {   
        super(context);   
    }    
       
    @Override  
    public boolean getDefaultEditable() {   
        return false;   
    }   
       
    // ���ѡ������
    public void clearSelection() {
    	Selection.removeSelection(getEditableText());
		bIsBeginSelecting = false;
		setCursorVisible(false);
    }
    
    public boolean isInSelectMode() {
    	return bIsBeginSelecting;
    }
}  