package com.example.wordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList;
    private final LinkedList<String> mKorList;
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            int mPosition = getLayoutPosition();

            String element = mWordList.get(mPosition);
            element = element.substring(0,2);

            String kor = "한글";

            if(element.equals(kor))
                mWordList.set(mPosition, "영어 " + mPosition);
            else
                mWordList.set(mPosition, mKorList.get(mPosition));

            mAdapter.notifyDataSetChanged();
        }
    }

    public WordListAdapter(Context context, LinkedList<String> wordList, LinkedList<String> mKorList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mKorList = mKorList;
    }

    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    public void onBindViewHolder(WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    public int getItemCount() {
        return mWordList.size();
    }
}