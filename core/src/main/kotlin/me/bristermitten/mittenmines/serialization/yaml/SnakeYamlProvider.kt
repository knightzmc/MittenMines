package me.bristermitten.mittenmines.serialization.yaml

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import javax.inject.Provider

class SnakeYamlProvider : Provider<Yaml> {
    override fun get(): Yaml {
        val options = DumperOptions()
        options.indent = 4
        options.isPrettyFlow = true
        options.defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
        options.splitLines = false
        return Yaml(options)
    }
}
