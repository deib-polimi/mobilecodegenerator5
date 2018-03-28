//
//  Utils.swift
//  MathKit
//
//  Created by Max on 26/03/2018.
//  Copyright © 2018 polimi. All rights reserved.
//

import Foundation

class Utils {
    static func truncateResult(_ input: String) -> String {
        if !input.contains(".") {
            return input
        }
        let splittedInput = input.components(separatedBy: ".")
        if splittedInput[1].characters.count <= 2 {
            return input
        }
        let range = (splittedInput[1].index(splittedInput[1].startIndex, offsetBy: 0))..<(splittedInput[1].index(splittedInput[1].startIndex, offsetBy: 2))
        return splittedInput[0] + "." + splittedInput[1].substring(with: range)
    }
}
