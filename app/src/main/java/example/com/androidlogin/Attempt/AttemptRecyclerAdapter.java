package example.com.androidlogin.Attempt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import example.com.androidlogin.R;
/**
 * Created by applelab1 on 7/12/18.
 */

public class AttemptRecyclerAdapter extends RecyclerView.Adapter<AttemptRecyclerAdapter.MyViewHolder> {
    private List<Attempt> attempts;

    public AttemptRecyclerAdapter(List<Attempt> attempts){
        this.attempts=attempts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_attempt,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.CourseCode.setText(attempts.get(position).getCoursecode());
        holder.CourseDesc.setText(attempts.get(position).getCoursedesc());
        holder.Attempt1.setText(attempts.get(position).getAttempt1());
        holder.Attempt2.setText(attempts.get(position).getAttempt2());
        holder.Attempt3.setText(attempts.get(position).getAttempt3());
        holder.Attempt4.setText(attempts.get(position).getAttempt4());
        holder.Attempt5.setText(attempts.get(position).getAttempt5());
    }


    @Override
    public int getItemCount() {
        return attempts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView CourseCode,CourseDesc,Attempt1,Attempt2,Attempt3,Attempt4,Attempt5;
        public MyViewHolder(View itemView) {
            super(itemView);

            CourseCode=(TextView)itemView.findViewById(R.id.coursecode);
            CourseDesc=(TextView)itemView.findViewById(R.id.coursedesc);
            Attempt1=(TextView)itemView.findViewById(R.id.attempt1);
            Attempt2=(TextView)itemView.findViewById(R.id.attempt2);
            Attempt3=(TextView)itemView.findViewById(R.id.attempt3);
            Attempt4=(TextView)itemView.findViewById(R.id.attempt4);
            Attempt5=(TextView)itemView.findViewById(R.id.attempt5);

        }
    }
}
