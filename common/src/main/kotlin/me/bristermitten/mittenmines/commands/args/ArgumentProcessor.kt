package me.bristermitten.mittenmines.commands.args

interface ArgumentProcessor<T> : TabCompleter, ArgumentContext<T>
