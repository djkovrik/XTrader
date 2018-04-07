package com.sedsoftware.wexchanger.commons

import android.content.Context
import android.graphics.drawable.Drawable
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import javax.inject.Inject

class IconsCollection @Inject constructor(private val context: Context) {

  val home = iconics(CommunityMaterial.Icon.cmd_home)
  val btc = iconics(CommunityMaterial.Icon.cmd_currency_btc)
  val wallet = iconics(CommunityMaterial.Icon.cmd_wallet)
  val tracker = iconics(CommunityMaterial.Icon.cmd_radar)
  val settings = iconics(CommunityMaterial.Icon.cmd_settings)

  private fun iconics(id: IIcon): Drawable =
    IconicsDrawable(context).icon(id)
}
