package top.rechinx.meow.glide

import com.bumptech.glide.load.Key
import top.rechinx.meow.data.database.model.Manga
import java.io.File
import java.security.MessageDigest

class MangaSignature(manga: Manga) : Key {

    private val key = manga.thumbnail_url!!

    override fun equals(other: Any?): Boolean {
        return if (other is MangaSignature) {
            key == other.key
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }

    override fun updateDiskCacheKey(md: MessageDigest) {
        md.update(key.toByteArray(Key.CHARSET))
    }
}