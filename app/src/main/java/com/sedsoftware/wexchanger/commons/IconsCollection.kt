package com.sedsoftware.wexchanger.commons

import android.content.Context
import android.graphics.drawable.Drawable
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.typeicons_typeface_library.Typeicons
import javax.inject.Inject

class IconsCollection @Inject constructor(private val context: Context) {

  val home = iconics(Typeicons.Icon.typ_home)
  val btc = iconics(CommunityMaterial.Icon.cmd_currency_btc)
  val wallet = iconics(CommunityMaterial.Icon.cmd_wallet)
  val tracker = iconics(CommunityMaterial.Icon.cmd_radar)
  val settings = iconics(Typeicons.Icon.typ_cog)

  private fun iconics(id: IIcon): Drawable =
    IconicsDrawable(context).icon(id)
}
