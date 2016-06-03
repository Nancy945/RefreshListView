# RefreshListView
下拉刷新和底部加载更多的自定义控件
      refreshListView = ((RefreshListView) findViewById(R.id.refreshListView));

        //允许下拉刷新
        refreshListView.setPullToRefreshEnable(true);
        //允许滚动到底部加载更多
        refreshListView.setSwipeToLoadMoreEnable(true);


        //刷新数据和加载更多的回调用法
        refreshListView.setOnRefreshDataListener(new RefreshListView.onRefreshDataListener() {
            @Override
            public void refreshData() {
                refreshListView.refreshStateFinish();
            });

            @Override
            public void loadingMore() {
                refreshListView.refreshStateFinish();
            }
        });
