import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivitySearchBinding
import com.example.myapplication.databinding.IvSearchRecommendBinding
import com.example.myapplication.databinding.IvSearchRecommnedMultiBinding

class SpannableActivity : AppCompatActivity() {

    private lateinit var binding : IvSearchRecommnedMultiBinding
    // TextView 변수
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        // 1. TextView 참조
        textView = binding.tvSearchRecommend

        // 2. String 문자열 데이터 취득
        val textData: String = textView.text.toString()

        // 3. SpannableStringBuilder 타입으로 변환
        val builder = SpannableStringBuilder(textData)

//        // 4-1. index=0 에 해당하는 문자열(0)에 볼드체적용
//        val boldSpan = StyleSpan(Typeface.BOLD)
//        builder.setSpan(boldSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        // 4-2 index=2에 해당하는 문자열(2)에 글자크기 3배 적용
//        val sizeBigSpan = RelativeSizeSpan(3.0f)
//        builder.setSpan(sizeBigSpan, 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 4-3 index=4에 해당하는 문자열(4)에 빨간색 적용
        val colorBlueSpan = ForegroundColorSpan(Color.RED)
        builder.setSpan(colorBlueSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 5. TextView에 적용
        textView.text = builder

    }
}