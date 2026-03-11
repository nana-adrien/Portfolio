package empire.digiprem.portfolio.design_system.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min

@Composable
fun SectionLayout(
    title: String?=null,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(modifier=Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(bottom = 40.dp), contentAlignment = Alignment.Center) {
        AdaptativeContainerLayout {
            Column(modifier=modifier,horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.spacedBy(20.dp)) {
                title?.let {
                    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
                content()
            }
        }
    }
}