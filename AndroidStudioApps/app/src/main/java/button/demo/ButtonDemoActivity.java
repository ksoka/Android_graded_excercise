
//  ButtonDemoActivity.java by Kari Laitinen

//  http://www.naturalprogramming.com/

//  2013-02-18  File created.
//  2019-10-10  Last modification.

/*  This is a simple Android application that shows a Button and
    a TextView on the screen. The text will change when the button
    is pressed.

    The files of this application include the following:

    app/src/main/java/button/demo/ButtonDemoActivity.java
    app/src/main/res/layout/activity_button_demo.xml
    app/src/main/res/values/strings.xml
    app/src/main/res/values-es/strings.xml

    If you want to run this app, you must set up an Android Studio
    project so that the package name is button.demo and the main
    activity is ButtonDemoActivity. After this you can copy the
    above-mentioned files to the corresponding locations in your
    project.

    Latest versions of Android Studio automatically set the name
    of the main activity to MainActivity. To run this app in a new
    Android Studio, you must modify the AndroidManifest.xml file
    so that you replace .MainActivity with .ButtonDemoActivity
    
*/

package button.demo ;

import android.os.Bundle ;
import android.app.Activity ;
import android.view.View ;
import android.widget.TextView ;
import android.widget.Button ;

public class ButtonDemoActivity extends Activity
{
   ButtonDemoView button_demo_view ;
   TextView textview_on_screen ;

   @Override
   public void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState ) ;
      setContentView( R.layout.activity_button_demo ) ;

      textview_on_screen = ( TextView ) findViewById( R.id.changing_text ) ;

      button_demo_view = (ButtonDemoView)
              findViewById( R.id.button_demo_view );
   }

   public void button_was_pressed( View view )
   {
      // Changing the text in the TextView is quite complicated
      // because we have to refer to the text defined in strings.xml
      // I could not find a way to get the resource id of the text
      // currently held in the TextField. For this reason we have
      // to compare strings here. This way of changing the text seems
      // to work at least with two languages.

      String current_text = (String) textview_on_screen.getText() ;

      String hello_text = getString( R.string.hello_text ) ;

      String well_done_text = getString( R.string.well_done_text ) ;
      button_demo_view.set_shape_to_draw("");

      if ( current_text.equals( hello_text ) )
      {
         textview_on_screen.setText( R.string.well_done_text ) ;
      }
      else
      {
         textview_on_screen.setText( R.string.hello_text ) ;
      }
   }
   public void second_button_was_pressed( View view )
   {
         textview_on_screen.setText("");
         button_demo_view.set_shape_to_draw("Circle");
   }
}
