package button.demo;

import android.view.*;
import android.os.Bundle;
import android.graphics.*;
import android.content.Context ;
import android.util.AttributeSet ;

class Circle
{
    int circle_center_point_x = 0 ;
    int circle_center_point_y = 0 ;
    int circle_color = Color.RED ;
    int circle_diameter = 128 ;
    boolean this_circle_is_activated = false ;
    public Circle( int given_center_point_x,
                   int given_center_point_y,
                   int given_color )
    {
        circle_center_point_x = given_center_point_x ;
        circle_center_point_y = given_center_point_y ;
        circle_color = given_color ;
    }
    public void activate_circle()
    {
        this_circle_is_activated = true ;
    }
    public void deactivate_circle()
    {
        this_circle_is_activated = false ;
    }
    public int get_circle_center_point_x()
    {
        return circle_center_point_x ;
    }
    public int get_circle_center_point_y()
    {
        return circle_center_point_y ;
    }
    public int get_circle_diameter()
    {
        return circle_diameter;
    }
    public void move_right()
    {
        circle_center_point_x += 3;
    }
    public void move_left()
    {
        circle_center_point_x  -=  3 ;
    }
    public void move_up()
    {
        circle_center_point_y  -=  3 ;
    }
    public void move_down()
    {
        circle_center_point_y  +=  3 ;
    }

    public void move_this_circle( int movement_in_direction_x,
                                  int movement_in_direction_y)
    {
        circle_center_point_x = circle_center_point_x + movement_in_direction_x ;
        circle_center_point_y = circle_center_point_y + movement_in_direction_y ;
    }
    public void move_to_position(int new_center_point_x,
                                 int new_center_point_y)
    {
        circle_center_point_x = new_center_point_x;
        circle_center_point_y = new_center_point_y;
    }

    public void shrink()
    {
        if (circle_diameter > 10)
        {
            circle_diameter -= 6 ;
        }
    }

    public void enlarge()
    {
        circle_diameter += 6 ;
    }

    public void set_diameter( int new_diameter )
    {
        if ( new_diameter > 5 )
        {
            circle_diameter = new_diameter ;
        }
    }

    public void set_color ( int new_color )
    {
        circle_color = new_color ;
    }
    public boolean contains_point( Point given_point )
    {
        int circle_radius = circle_diameter / 2 ;
        int distance_form_given_point_to_circle_center =
                (int) Math.sqrt(
                        Math.pow(circle_center_point_x - given_point.x, 2) +
                        Math.pow(circle_center_point_y -given_point.y, 2)
                );
        return (distance_form_given_point_to_circle_center <= circle_radius) ;
    }
    public void draw( Canvas canvas )
    {
        Paint filling_paint = new Paint();
        filling_paint.setStyle( Paint.Style.FILL );
        filling_paint.setColor(circle_color) ;
        Paint outline_paint = new Paint() ;
        outline_paint.setStyle( Paint.Style.STROKE );

        canvas.drawCircle( circle_center_point_x, circle_center_point_y,
                circle_diameter / 2, filling_paint);
        if (this_circle_is_activated == true )
        {
            outline_paint.setStrokeWidth(3);
        }
        canvas.drawCircle(circle_center_point_x,circle_center_point_y,
                circle_diameter/2,filling_paint);

    }

}

final class ButtonDemoView extends View {

    int view_width, view_height ;
    Circle first_circle;
    Circle circle_being_moved = null;
    Point previous_pointer_position;

    public ButtonDemoView( Context context )
    {
        super( context ) ;
    }
    public void onSizeChanged( int current_width_of_this_view,
                               int current_height_of_this_view,
                               int old_height_of_this_view,
                               int old_width_of_this_view)
    {
        view_width = current_width_of_this_view;
        view_height = current_height_of_this_view;
        first_circle = new Circle( view_width / 2,
                                   view_height / 2 - 150,
                                    Color.RED) ;
    }
    public boolean onTouchEvent (MotionEvent motion_event)
    {
        if (motion_event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Point pointer_position = new Point ((int) motion_event.getX(),
                                                (int)motion_event.getY() );
            circle_being_moved = first_circle;
            circle_being_moved.activate_circle();
            previous_pointer_position = pointer_position;
        }
        else if (motion_event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if (circle_being_moved != null)
            {
                Point new_pointer_position = new Point((int) motion_event.getX(),
                                                        (int) motion_event.getY());
                int pointer_movement_x = new_pointer_position.x - previous_pointer_position.x;
                int pointer_movement_y = new_pointer_position.y - previous_pointer_position.y;
                previous_pointer_position = new_pointer_position;
                circle_being_moved.move_this_circle(pointer_movement_x, pointer_movement_y);

            }
        }
        else if ( motion_event.getAction() == MotionEvent.ACTION_UP )
        {
            if (circle_being_moved != null)
            {
                circle_being_moved.deactivate_circle();
                circle_being_moved = null;
            }
        }
        invalidate();
        return true;
    }


    String shape_to_draw = "Hello" ;
    public void set_shape_to_draw( String given_shape_to_draw )
    {
        shape_to_draw = given_shape_to_draw ;
        invalidate();
    }
    @Override
    protected void onDraw( Canvas canvas ) {
        if (shape_to_draw.equals("Circle")) {
            first_circle.draw(canvas);
        }
    }

    public ButtonDemoView( Context context, AttributeSet attributes )
    {
        super( context, attributes ) ;
    }
}
