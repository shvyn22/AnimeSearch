package shvyn22.animesearch.util

class ActionsState(
    private var deleteBookmarks: () -> Unit = {}
) {
    fun setDeleteBookmarksAction(callback: () -> Unit) {
        deleteBookmarks = callback
    }

    fun executeDeleteBookmarksAction() {
        deleteBookmarks.invoke()
    }
}