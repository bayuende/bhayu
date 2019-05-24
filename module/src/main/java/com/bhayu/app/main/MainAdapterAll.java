package com.bhayu.app.main;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhayu.app.base.BaseListAdapter;
import com.bhayu.app.base.BaseViewHolder;
import com.bhayu.app.model.News;
import com.bhayu.module.R;
import com.squareup.picasso.Picasso;

public class MainAdapterAll extends BaseListAdapter<News, MainAdapterAll.MainAdapterAllHolder> {
    public MainAdapterAll(Context context)
    {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_all;
    }

    @Override
    public MainAdapterAllHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainAdapterAllHolder(getView(parent,viewType),onItemClickListener);
    }

    public class MainAdapterAllHolder extends BaseViewHolder<News>
    {
        private TextView tvAuthor,tvTitle,tvShortDesc,tvLike,tvComment;
        private ImageView ivImg;
        public MainAdapterAllHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);

            ivImg = itemView.findViewById(R.id.ivImg);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvShortDesc = itemView.findViewById(R.id.tvShortDesc);
            tvLike = itemView.findViewById(R.id.tvLike);
            tvComment = itemView.findViewById(R.id.tvComment);
        }

        @Override
        public void bind(News item) {
            Picasso.with(context)
                    .load(item.getImageUrl())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivImg);

            tvAuthor.setText(""+item.getAuthor());
            tvTitle.setText(""+item.getTitle());
            tvShortDesc.setText(""+ Html.fromHtml(item.getShortDescription()));
            tvLike.setText(""+item.getLikeCount());
            tvComment.setText(""+item.getCommentCount());
        }
    }
}
