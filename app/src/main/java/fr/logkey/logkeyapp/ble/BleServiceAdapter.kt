package fr.logkey.logkeyapp.ble

import android.bluetooth.BluetoothGattCharacteristic
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import fr.logkey.logkeyapp.R
import java.util.*


class BleServiceAdapter(
    val context: Context,
    val serviceList: MutableList<BleService>,
    private val readCharacteristicCallback: (BluetoothGattCharacteristic) -> Unit,
    private val writeCharacteristicCallback: (BluetoothGattCharacteristic) -> Unit,
):ExpandableRecyclerViewAdapter<BleServiceAdapter.ServiceViewHolder, BleServiceAdapter.CharacteristicViewHolder>(
    serviceList
)



{


    class ServiceViewHolder(itemView: View) : GroupViewHolder(itemView) {
        var serviceName: TextView = itemView.findViewById(R.id.serviceName)
        var serviceUUID: TextView = itemView.findViewById(R.id.serviceUUID)
        var serviceArrow: TextView = itemView.findViewById(R.id.serviceArrow)

    }

    class CharacteristicViewHolder(itemView: View) : ChildViewHolder(itemView) {
        var characteristicName: TextView = itemView.findViewById(R.id.characteristicName)
        var characteristicUUID: TextView = itemView.findViewById(R.id.characteristicUUID)
        var readAction: Button = itemView.findViewById(R.id.readAction)
        var writeAction: Button = itemView.findViewById(R.id.writeAction)
        val characteristicProperties : TextView = itemView.findViewById(R.id.characteristicProperties)
        val characteristicValue : TextView = itemView.findViewById(R.id.characteristicValue)
        val lectureBtn : Button = itemView.findViewById(R.id.readAction)
        val ecrireBtn : Button = itemView.findViewById(R.id.writeAction)
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_ble_service, parent, false)
        return ServiceViewHolder(itemView)
    }

    override fun onCreateChildViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacteristicViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_ble_characteristic, parent, false)
        return CharacteristicViewHolder(itemView)
    }

    override fun onBindChildViewHolder(
        holder: CharacteristicViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>,
        childIndex: Int
    ) {
        val characteristic = group.items[childIndex] as BluetoothGattCharacteristic
        holder.characteristicName.text = characteristic.uuid.toString()

        val title = BLEUUIDAttributes.getBLEAttributeFromUUID(characteristic.uuid.toString()).title

        val uuidMessage = "UUID : ${characteristic.uuid}"
        holder.characteristicUUID.text = uuidMessage

        holder.characteristicName.text = title
        val properties = arrayListOf<String>()

        addPropertyFromCharacteristic(
            characteristic,
            properties,
            "Lecture",
            BluetoothGattCharacteristic.PROPERTY_READ,
            holder.lectureBtn,
            readCharacteristicCallback
        )

        addPropertyFromCharacteristic(
            characteristic,
            properties,
            "Ecrire",
            (BluetoothGattCharacteristic.PROPERTY_WRITE or BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE),
            holder.ecrireBtn,
            writeCharacteristicCallback
        )



        val proprietiesMessage = "Proprietés : ${properties.joinToString()}"
        holder.characteristicProperties.text = proprietiesMessage
        characteristic.value?.let {
            val hex = it.joinToString("") { byte -> "%02x".format(byte)}.uppercase(Locale.FRANCE)
            val value = "Valeur : ${String(it)} Hex : 0x$hex"
            holder.characteristicValue.visibility = View.VISIBLE
            holder.characteristicValue.text = value
        }
    }
    private fun addPropertyFromCharacteristic(
        characteristic: BluetoothGattCharacteristic,
        properties: ArrayList<String>,
        propertyName: String,
        propertyType: Int,
        propertyAction: Button,
        propertyCallBack: (BluetoothGattCharacteristic) -> Unit
    ) {
        if ((characteristic.properties and propertyType) != 0) {
            properties.add(propertyName)
            propertyAction.isEnabled = true
            propertyAction.setTextColor(ContextCompat.getColor(context, R.color.or))

            propertyAction.setOnClickListener {
                propertyCallBack.invoke(characteristic)
            }
        }
    }



    fun updateFromChangedCharacteristic(characteristic: BluetoothGattCharacteristic?) {
        serviceList.forEach {
            val index = it.items.indexOf(characteristic)
            if(index != -1) {
                it.items.removeAt(index)
                it.items.add(index, characteristic)
            }
        }
    }

    override fun onBindGroupViewHolder(
        holder: ServiceViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>
    ) {
        holder.serviceName.text = group.title
    }
}