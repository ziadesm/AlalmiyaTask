package com.task.alalmiyatask.utils
import com.google.common.truth.Truth.assertThat
import com.task.alalmiyatask.pojo.CharacterModel
import org.junit.Test

class CharacterValidationTest{

    @Test
    fun `expect 6 items without space`() {
        val result = CharacterValidation.getCharacterValidationList("   DRRE   \n  78  Bbbf Bbbf HTML html Html html")

        assertThat(result).hasSize(6)
    }

    @Test
    fun `expect no duplication in list`() {
        val result = CharacterValidation.getCharacterValidationList("   DRRE    +00 78 00 Bbbf Bbbf HTML html Html html")

        assertThat(result).containsNoDuplicates()
    }

    @Test
    fun `expect 9 items with special characters`() {
        val result = CharacterValidation.getCharacterValidationList("   DRRE    +00 78 00 Bbbf Bbbf HTML html Html html")

        assertThat(result).hasSize(8)
    }

    @Test
    fun `find HTML WORD to make sure`() {
        val result = CharacterValidation.getCharacterValidationList("   DRRE  +  +00 78 00 Bbbf Bbbf HTML html Html html")

        assertThat(result).contains(CharacterModel("html", 2))
    }

    @Test
    fun `check null string value`() {
        val result = CharacterValidation.getCharacterValidationList(null)

        assertThat(result).hasSize(1)
    }
}