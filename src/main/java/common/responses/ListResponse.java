package common.responses;

import java.util.List;

public class ListResponse<T> {

	private List<T> content;
	private long totalCount;

	public ListResponse(List<T> content) {
		this.content = content;
		this.totalCount = content.size();
	}

	public List<T> getContent() {
		return content;
	}

	public long getTotalCount() {
		return totalCount;
	}
}
