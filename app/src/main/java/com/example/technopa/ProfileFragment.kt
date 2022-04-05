import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.technopa.EditDialogFragment
import com.example.technopa.R
import com.example.technopa.databinding.ProfileLayoutBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.profile_layout.*
import kotlinx.coroutines.supervisorScope

class ProfileFragment: Fragment() {
    private lateinit var binding: ProfileLayoutBinding

    var user = User()

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProfileLayoutBinding.inflate(inflater, container,  false)

        binding.nameTv.text = user.name
        binding.surnameTv.text = user.surname
        binding.heightValueTv.text = user.height.toString()
        binding.weightValueTv.text = user.weight.toString()
        binding.desiredWeightValueTv.text = user.desired_weight.toString()


        //change information
        binding.editTv.setOnClickListener(){
            val dialog = EditDialogFragment(user)
            childFragmentManager.let { it1 -> dialog.show(it1, "EditDialog") }
        }

        //progress bar
        binding.progressBar.max = user.weight.toInt()
        binding.progressBar.setProgress(user.desired_weight.toInt(),true)
        binding.progressValueTv.text = (((user.desired_weight/user.weight)*100).toInt()).toString() + "%"

        return binding.root
    }



    class User (){
        //val id:Long
        var name: String = "Vyacheslav"
        var surname: String = "Gorlov"
        var height : Int = 180
        var weight : Double = 95.0
        var desired_weight : Double = 85.0

        //val statistic: Statistic,
        //val achievment: List<Achievment>
        }
}