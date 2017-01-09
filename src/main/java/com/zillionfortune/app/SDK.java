package com.zillionfortune.app;

import com.growingio.growingapi.GrowingDownloadApi;

public class SDK {
	public static void main(String[] args) {
		GrowingDownloadApi api = new GrowingDownloadApi();
		api.download("2017010518");
	}
}
