package top.rechinx.meow.ui.details

import android.content.Context
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible
import top.rechinx.meow.data.database.model.Chapter
import top.rechinx.meow.data.database.model.Manga
import top.rechinx.meow.ui.details.items.ChapterItem
import top.rechinx.meow.ui.details.items.HeaderItem


class DetailAdapter(context: Context): FlexibleAdapter<IFlexible<*>>(null, context, true) {

    lateinit var onLoadMoreListener: OnLoadMoreListener

    var latestChapterId: Long = 0

    fun setLast(id: Long) {
        if(id == latestChapterId) return
        var tmp = latestChapterId
        latestChapterId = id
        for(i in 0 until itemCount) {
            val item  = getItem(i)
            if(item is ChapterItem) {
                if(item.chapter.id == latestChapterId) {
                    updateItem(i, item, null)
                } else if(item.chapter.id == tmp) {
                    updateItem(i, item, null)
                }
            }
        }
    }

    fun setMangaLastUpdated(manga: Manga) {
        val headerItem = getItem(0) as HeaderItem
        headerItem.manga.last_update = manga.last_update
        updateItem(0, headerItem, null)
    }

    fun getChapters() : ArrayList<Chapter> {
        val list = ArrayList<Chapter>()
        for(i in 0 until itemCount) {
            val item = getItem(i)
            if(item is ChapterItem) {
                list.add(item.chapter)
            }
        }
        return list
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}