package example.com.androidlogin.Result;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.androidlogin.R;

/**
 * Created by applelab1 on 7/9/18.
 */


public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.MyViewHolder>
{
    private List<Result> results;

    public ResultRecyclerAdapter(List<Result> results){
    this.results=results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_result,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Grade.setText(results.get(position).getGrade());
        holder.CourseCode.setText(results.get(position).getCoursecode());
        holder.CourseDesc.setText(results.get(position).getCoursedesc());
    }


    @Override
    public int getItemCount() {
        return results.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Grade,CourseCode,CourseDesc;
        public MyViewHolder(View itemView) {
            super(itemView);
            Grade=(TextView)itemView.findViewById(R.id.grade);
            CourseCode=(TextView)itemView.findViewById(R.id.coursecode);
            CourseDesc=(TextView)itemView.findViewById(R.id.coursedesc);
        }
    }
}